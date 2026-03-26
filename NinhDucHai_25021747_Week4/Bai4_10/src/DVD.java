// 3. Lớp DVD
class DVD extends MediaItem {
    private String director;
    private int duration;

    public DVD(String id, String name, String director, int duration) {
        super(id, name);
        this.director = director;
        this.duration = duration;
    }

    @Override
    public void displayInfo() {
        System.out.println(name + " - " + director + " - " + duration);
    }
}
