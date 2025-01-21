// Consider the following code and assume the numbers at the left are line numbers, not part of the source file

/*11:*/ public class Ex2<T extends Runnable, String> {
/*12:*/   // String s = "Hello";
/*13:*/    public void test(T t) {
    /*14:*/       t.run();
        /*15:*/    }
/*16:*/}

/*
a. Line 11 fails to compile.        MY ANSWER
b. Line 12 fails to compile.
c. Line 13 fails to compile.
d. Line 14 fails to compile.
e. Compilation succeeds.
 */

/*
Your answer is incorrect.
Java’s generics mechanism provides powerful consistency checking during compilation. That is, generics allow
the programmer to declare ideas such as “I intend to use this generic object with Fruits,” and then the compiler
verifies that all uses of that object are consistent with the Fruit intention. During compilation,
a process called type erasure removes most of the type information; however, compile-time checking is sufficient
for a large majority of situations.

In general, a generic class can be declared, along with generic type variables, using the following form:

public class Pair<T> {
   private T left, right;
   ...
}
Notice that after declaring T in the angle brackets, T maybe used as the placeholder for a type in the
declaration of the variables left and right. Multiple type variables may be declared in a comma-separated list,
for example

public interface Map<K, V> ...
However, a naive approach to type erasure can be inadequate if your code needs to use some knowledge
about the generic type. Consider this extension of the example.

interface Sized { int getSize(); }
public class Pair<T> {
   private T left, right;
   public boolean matched() {
      return left.getSize() == right.getSize(); // line n1
   }
}
With type erasure, the comparison of the sizes in line n1 will not work, because left and right are treated
as simple Object types, which don’t have the necessary getSize() methods. You can rectify that by using
the following syntax:

interface Sized { int getSize(); }
public class Pair<T extends Sized> {
   private T left, right;
   public boolean matched() {
      return left.getSize() == right.getSize(); // line n1
   }
}

The syntax <T extends Sized> constrains T to be something that implements Sized and, therefore,
you (and the compiler) know that you can invoke the getSize method on it. In fact, this syntax can
be extended to place multiple constraints on the generic type, as follows:

interface Sized { int getSize(); }
interface Colored { Color getColor(); }
public class Pair<T extends Sized & Colored> {
   private T left, right;
   public boolean matched() {
      return left.getSize() == right.getSize()
         && left.getColor().equals(right.getColor());
   }
}
Notice the syntax this time is <T extends Sized & Colored>, which requires T to implement both interfaces and,
therefore, allows the matched method to perform both tests successfully. Also, one class can be mentioned
in this list, but if that happens, the class must be first on the list. It’s perhaps obvious, but Java’s
single-implementation inheritance rule makes it impossible to specify more than a single class,
even though multiple interfaces might be relevant.

Given the statement above noting that if a class is mentioned in the list it must come first,
does this mean that line 11 fails to compile, because the line mentions Runnable first and String second?
No; it does not, because line 11 doesn’t use a multiple-constraints syntax. Look closely: it uses a comma.
That comma means that two type variables are being declared, T and String, and the declaration is correct,
which in turn means that line 11 compiles. Therefore, option A is incorrect.

The first of the declared generic type variables, T, is constrained to be something that implements Runnable.
(By the way, there’s an informal convention that T is usually short for type when you are using generics.)
The type variable T is correctly declared and constrained, which means that the use of T as an argument
in line 13 is correct. Thus, option C is incorrect.

Because the type variable T carries the constraint extends Runnable, the invocation of t.run() on line 14
is correct, and thus you know option D is incorrect.

In addition to declaring T with a single constraint, the comma separation in line 11 also declares another
generic type variable that has a very unwise name: String. This is a generic type variable, and it has
nothing to do with java.lang.String. Because String is not constrained in any way, its base representation
is Object, and it’s not possible to know what type can be assigned to String until someone makes use of the class.
Imagine that the following declaration is made:

class MyJob implements Runnable { public void run() {} }
Ex2<MyJob, LocalDate> ex2;
In this context, the type variable called String (remember, it’s not a java.lang.String) is supposed to be LocalDate.
It should now be clear why you cannot assign the literal java.lang.String object "Hello" to it and, therefore,
why line 12 fails to compile. Therefore, option B is the correct answer. (Of course, because line 12 fails to compile,
you know that option E is also incorrect.)

Although the namespace of generic type variables is totally different from the namespace of non-generic types
(such as classes and interfaces), ambiguities such as the one with the meaning of String led to the informal
 convention that type variables should generally be single uppercase letters. We hope this question convinces
 you of the importance of adhering to this convention.

The correct answer is: Line 12 fails to compile.
 */