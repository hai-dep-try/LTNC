class DataManager implements IData {
    // Sửa lỗi: Thêm từ khóa 'public' để không thu hẹp phạm vi truy cập
    @Override
    public void show() {
        System.out.println("Show Data");
    }
}
