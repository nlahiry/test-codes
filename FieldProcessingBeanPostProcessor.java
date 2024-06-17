public class FieldProcessingBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private HttpServletRequest request; // Assuming request is injected through a suitable mechanism (e.g., WebMvcConfigurer or custom interceptor)

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldProcessor.class)) {
                try {
                    field.setAccessible(true);
                    FieldProcessor annotation = field.getAnnotation(FieldProcessor.class);
                    Object value = field.get(bean);
                    String processingFunction = getProcessingFunction(annotation.functionName(), getEndpointName());
                    Object processedValue = processValue(processingFunction, value);
                    field.set(bean, processedValue);
                } catch (Exception e) {
                    // Handle exceptions appropriately
                }
            }
        }
        return bean;
    }

    private String getEndpointName() {
        // Extract the endpoint name from the HttpServletRequest object (e.g., using request.getRequestURI())
        return /* logic to extract endpoint name from request */;
    }

    private String getProcessingFunction(String baseFunctionName, String endpointName) {
        // Implement logic to determine processing function based on projectName and baseFunctionName
        // You can use a map or conditional statements to associate endpoints with processing functions
        return projectName + baseFunctionName; // Placeholder for endpoint-specific logic
    }

    // ... existing processValue method ...
}
