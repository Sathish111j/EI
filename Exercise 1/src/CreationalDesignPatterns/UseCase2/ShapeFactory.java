package CreationalDesignPatterns.UseCase2;

public class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null || shapeType.isEmpty()) throw new IllegalArgumentException("Shape type cannot be null or empty");
        if ("circle".equalsIgnoreCase(shapeType)) {
            return new Circle();
        } else if ("square".equalsIgnoreCase(shapeType)) {
            return new Square();
        } else {
            throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
}
