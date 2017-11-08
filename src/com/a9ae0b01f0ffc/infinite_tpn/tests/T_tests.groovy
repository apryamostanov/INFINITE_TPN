import org.junit.Test
import other.T_round_robin

class T_tests {

    @Test
    void test_01() {
        ArrayList<String> l_strings = new ArrayList<String>()
        l_strings.add("1")
        l_strings.add("2")
        l_strings.add("3")
        T_round_robin<String> l_round_robin = new T_round_robin<String>(l_strings)
        while (true) {
            String z = ++l_round_robin.iterator()
            System.out.println(z)
        }
    }

}
