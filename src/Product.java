public class Product {
    int id;
    String name;

    public Product(int i, String n) {
        id = i;
        name = n;
    }

    /*@Override
    WRONG CODE
    public boolean equals(Product p) {
        return (p != null)
                && (p.id==id)
                && (p.name.equals(name));
    }*/

    /* TODO
    Lombok @EqualsAndHashCode
    Maven project

     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;     // product.equals(product) roughly equivalent to this.equals(o)
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;      // only after we established that the two are of the same class, otherwise runtime exception
        return id == product.id && name.equals(product.name);
    }

    // If two objects are equal according to their equal method, then they must have the same hash code,
    // but if two objects have the same hash code, they may or may not be equal
}

/*
Your answer is correct.
This question exemplifies a style that is quite common in the Java certification exam. The code shown is deliberately designed to hint that the question is about Set. However, this question actually investigates method overriding and the proper use of the @Override annotation.

Before you cry foul, remember that this is a very realistic situation: You are debugging some code and become fixated on one element of it, confident that the problem is there. Eventually, you discover that the problem was actually elsewhere—but only after you wasted a lot of time.

To override a method, you must create a method in a subtype of the type that contains the original method. The method in the subtype must have the same name and argument type sequence plus a compatible return type, accessibility, and throws clause. If the return type, accessibility, or throws clauses are incompatible, the compiler will reject the code.

However, in the most basic case, if you actually misspelt the name of the method, or if the argument type sequence is different, you will not get an error. This is because a misspelling simply creates a different method, while a different argument type sequence creates an overloading method instead of an overriding method. The absence of an error message can be annoying, and in Java 5, the @Override annotation was added so that an error can be forced in this situation.

The code in this question uses the @Override annotation to declare the programmer’s intention that the equals method in Product should be an overriding method. However, the formal parameter of this method is of type Product; consequently, this is not a valid override of the Object.equals(Object o) method of the parent class (which is Object). As a result, the @Override annotation causes the compiler to reject the Product class. This means option D is correct.

What if the annotation were omitted? This would create a valid overloaded method and the class would compile. Unfortunately, that overloaded method would have no significance to the Java collection classes, resulting in wasted debugging time as you try to find why the code isn’t doing what you intended.

Option A is definitely incorrect because the code does put at least one item into the set, so the element count cannot possibly be zero.

If you were to correct the signature and implementation of the equals method so the code compiles and the equals method correctly identifies duplicates, the result will be a failure at runtime. The Set.of factory methods are documented as throwing an IllegalArgumentException if any duplicate elements are provided in the argument list. From this you can determine that options B and C are also incorrect.

If you further modify the code to use HashSet instead of the unmodifiable set created by the Set.of factory, the IllegalArgumentException can be avoided. However, in this situation the result would probably be surprising: The code would print 2. This is because although you provided a workable equals method, you did not provide a hashCode method. The HashSet uses a hashCode method to determine definite inequality before using the equals method to determine if objects that might be equal actually are equal. Because of this, the HashSet will determine that the two Product objects are unique without ever calling their equals methods. It will, therefore, keep both of them.

If, in addition to modifying the Set and providing a correct override of equals, you also implement a proper hashCode method in the Product class, the HashSet will correctly reject the second Product as a duplicate, the set will contain just one element, and you would get the expected output of 1.

It’s worthwhile to note that if the Product class had been implemented as a record in Java 16 or newer, the constructor, as well as the equals and hashCode methods, would have been provided automatically. The record implementation would look like the following:

public record Product(int id, String name) {}
However, since none of those proposed changes were applied, option D is the correct answer, and options A, B, C, and E are incorrect.

The correct answer is: Compilation of the Product class fails.
 */
