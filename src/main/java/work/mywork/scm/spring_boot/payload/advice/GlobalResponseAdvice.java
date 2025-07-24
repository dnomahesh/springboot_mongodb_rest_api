package work.mywork.scm.spring_boot.payload.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import work.mywork.scm.spring_boot.payload.ApiResponse;

import java.util.*;

@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // If already wrapped, return as-is
        if (body instanceof ApiResponse<?>) return body;

        
        // Handle List<T> manually if ?page and ?size are provided
        if (body instanceof List<?> list) {
            HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String pageStr = servletRequest.getParameter("page");
            String sizeStr = servletRequest.getParameter("size");

            if (pageStr != null && sizeStr != null) {
                try {
                    int page = Integer.parseInt(pageStr);
                    int size = Integer.parseInt(sizeStr);
                    int totalItems = list.size();
                    int totalPages = (int) Math.ceil((double) totalItems / size);

                    int fromIndex = page * size;
                    int toIndex = Math.min(fromIndex + size, totalItems);

                    List<?> pagedData = fromIndex >= totalItems ? Collections.emptyList() : list.subList(fromIndex, toIndex);

                    Map<String, Object> pagination = Map.of(
                            "currentPage", page,
                            "totalItems", totalItems,
                            "totalPages", totalPages,
                            "pageSize", size
                    );

                    Map<String, Object> responseBody = Map.of(
                            "data", pagedData,
                            "page", pagination
                    );

                    return responseBody;
                    // return new ApiResponse<>(pagedData);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    // Fall through to return full list without pagination
                }
            }

            // No valid pagination params? Return full list
            return new ApiResponse<>(Map.of("data", list, "page", null));
        }

        // Handle Page<T>
        if (body instanceof Page<?> pageData) {
            Map<String, Object> pagination = Map.of(
                    "currentPage", pageData.getNumber(),
                    "totalItems", pageData.getTotalElements(),
                    "totalPages", pageData.getTotalPages(),
                    "pageSize", pageData.getSize()
            );

            Map<String, Object> responseBody = Map.of(
                    "data", pageData.getContent(),
                    "page", pagination
            );

            return responseBody;
            // return new ApiResponse<>(pageData.getContent());
        }

        // All other cases
        return new ApiResponse<>(body);
    }
}

