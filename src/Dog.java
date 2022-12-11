class Dog implements Comparable<Dog> {

    private final String nickname;

    public Dog(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return nickname;
    }

    @Override
    public int compareTo(Dog d) {
        return this.nickname.compareTo(d.nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        return nickname.equalsIgnoreCase(dog.nickname);
    }

    @Override
    public int hashCode() {
        return nickname.hashCode();
    }
}