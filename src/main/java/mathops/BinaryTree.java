package mathops;

import java.util.Objects;

public class BinaryTree {
    private final String value;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(String value) {
        this.value = value;
    }

    public BinaryTree(String value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public BinaryTree getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree that = (BinaryTree) o;
        return Objects.equals(value, that.value) && Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "value='" + value + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
