package multimediaManager;

public class Path {
    protected String name;

    public Path(){};

    public Path(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("name='").append(name).append('\'');

        return sb.toString();
    }
}
