// 3. Custom Functional Interface (Tạo ở ngoài hoặc trong class đều được)
@FunctionalInterface
interface Operation<T> {
    T execute(T a, T b);
}
