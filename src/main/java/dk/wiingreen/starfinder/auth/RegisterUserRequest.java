package dk.wiingreen.starfinder.auth;

record RegisterUserRequest(String username, String password) {
    @Override
    public String toString() {
        return "RegisterUserRequest{username='%s'}".formatted(password);
    }
}
