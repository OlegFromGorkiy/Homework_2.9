import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private final Map<String, Long> data = new HashMap<>();
    // было капсом потому что final.
    // Я почему-то думал что все final переменные надо называть капсом,например Integer.MAX_VALUE;


    public Long getNumber(String name) {
        // скобки хуебки. В двух строчках запутаться???
        if (data.containsKey(name)) {
            return data.get(name);
        } else {
            System.out.println("Subscriber not found!");
        }
        return null;
    }

    public void addSubscriber(String name, Long number) {
        //уж до чего, а до названия методов раньше никто не доебывался. Ну ладно, переименую.
        data.put(name, number);
    }

    public void addSubscriber(String name, String number) {
        String s = number.replaceAll("\\D", "");
        long numb = Long.parseLong(s);
        data.put(name, numb);
    }

    public void removeSubscriber(String name) {
        data.remove(name);
    }

    public void printInfo() {
        int count = 0;
        for (String str : data.keySet()) {
            count++;
            StringBuilder sb = new StringBuilder(count + ". " + str + ": ");
            sb.append(numberFormat(data.get(str)));
            System.out.println(String.format("%d. %s: %s", count, str, numberFormat(data.get(str))));
        }
    }

    private String numberFormat(Long number) {
        String numb = Long.toString(number);
        if (numb.length() < 11) {
            String tmp = "";
            for (int i = 0; i < 11 - numb.length(); i++) {
                tmp = "_" + tmp;
            }
            numb = tmp + numb;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("+").append(numb.substring(0, numb.length() - 10)); // country code
        sb.append(" (").append(numb.substring(numb.length() - 10, numb.length() - 7)).append(") "); // provider code
        sb.append(numb.substring(numb.length() - 7, numb.length() - 4)).append("-");// first three digits
        sb.append(numb.substring(numb.length() - 4, numb.length() - 2)).append("-");// second two digits
        sb.append(numb.substring(numb.length() - 2));// last two digits
        return sb.toString();
    }
}
