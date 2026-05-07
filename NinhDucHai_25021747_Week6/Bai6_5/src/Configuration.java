class Configuration implements Cloneable {
    private String theme;
    private String language;

    public Configuration(String theme, String language) {
        this.theme = theme;
        this.language = language;
    }

    public void setTheme(String theme) { this.theme = theme; }

    public void printConfig() {
        System.out.println("Theme: " + theme + ", Language: " + language);
    }

    @Override
    public Configuration clone() {
        try {
            return (Configuration) super.clone(); // Shallow copy là đủ vì chỉ dùng kiểu String
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}