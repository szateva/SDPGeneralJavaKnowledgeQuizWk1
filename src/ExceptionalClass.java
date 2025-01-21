public class ExceptionalClass {
    void doSomethingGood() {
        try {
            doSomethingBad();
            System.out.print("All Good"); // line n1
        }
        catch(FooException fe) {      // line n2
        }
        catch(BarException be) {
        }
        catch(Exception e) {          // line n3
        }
    }
    void doSomethingBad() throws FooException {
        throw new FooException("Something Bad");
    }
}