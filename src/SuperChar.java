public class SuperChar {
    //static final char zero; // line n2
    private transient final char one = '1'; // line n3

    public SuperChar() {
       // System.out.print("'" + zero + one + "'");
    }

    public static void main(String... args) {
        new SuperChar();
    }
}

/*
Compilation fails due to line n2

Your answer is correct.
Let’s take care of the lowest-hanging fruit first. The set of modifiers on line n3 is entirely valid and
the line does not cause a compilation error. Thus, option g is incorrect.

Default initialisation of variables is a popular topic for the Java certification exam.
Generally, every field in an object or class is initialised to a zero-like value during the allocation of memory.
This aspect is, in fact, inseparable from the action of new and ensures that numeric primitive types start out
with zero values, object types start out as null, and boolean types start out false. In most situations,
if no further explicit initialisation is provided by the code, a field will simply remain that zero-like value.

However, there is an exception to this behaviour for final fields, which are required to be explicitly initialised.
If this is not done, the code will fail to compile.

In many cases, final variables are initialised immediately in the declaration statement; this approach is convenient
and obviously correct. However, if this is not done, a final field must be initialised later. If such delayed
initialisation is used, the field is referred to as a blank final. This is discussed in Java Language Specification
section 8.3.1.2, which makes two particular points.

A blank final class variable must be definitely assigned by a static initialiser of the class in which it is declared,
or a compile-time error occurs.

A blank final instance variable must be definitely assigned and moreover not definitely unassigned at the end
of every constructor of the class in which it is declared, or a compile-time error occurs.

The first of these points describe blank final class variables. In the quiz code, line n2 declares just
such a final class variable.

static final char zero;
Clearly, this declaration does not initialise the zero variable in the declaration, but further,
there is nothing in any static initialiser block that does so either. Because of this, the zero field
is never explicitly assigned a value, and the class will not compile. This means option f is correct and
the remaining options incorrect.

Let’s consider some variations. First, imagine that the final modifier was not included.
In that case, normal default initialisation would apply, the code would compile successfully,
and the value of zero would, in fact, be numerically 0. If that change were made, the string
concatenation that precedes printing the output would first concatenate the opening single quote
mark with the value of zero, which is the char with numeric value 0; and
the second concatenation would append the character 1 and then the closing single quote mark.
The representation of the zero character is platform-dependent. Sometimes it is represented as a space,
sometimes it produces no output, and other possibilities exist. Of the options listed, two possibilities are shown:
in option a, ' 1' (which has a space before the 1), and in option c, (which does not have a space).
The char-type variable zero will not be presented as a digit 0, nor will the value be promoted to an int
since it is explicitly a char type. Therefore, option b would still be incorrect.

Side note: When a value explicitly typed as a char is concatenated with a string, the character is
concatenated—the char is not promoted to int. By contrast, if two char values are added together,
they will both be promoted to int as part of the normal numeric operand promotion. This means that
you will not see 49 in the output with the code as it is. However, what if the print line were
changed to look like this?

System.out.print("'" + (zero + one) + "'");
Then the operands zero and one would both be promoted to int type, their values would be summed to 49,
that sum would be converted from an int back into a textual representation of that number, and the output
would be '49'. However, since that’s not the case here, options d and e are incorrect.

The correct answer is: Compilation fails due to line n2
 */