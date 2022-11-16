import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private final Map<String, Long> DATA = new HashMap<>();

    public Long getNumber(String name) {
        if (DATA.containsKey(name)) return DATA.get(name);
        else System.out.println("Subscriber not found!");
        return null;
    }

    public void put(String name, Long number) {
        DATA.put(name, number);
    }

    public void put(String name, String number) {
        String s = number.replaceAll("\\D", "");
        long numb = Long.parseLong(s);
        DATA.put(name, numb);
    }

    public void remove(String name) {
        DATA.remove(name);
    }

    public void printInfo() {
        int count = 0;
        for (String str : DATA.keySet()) {
            count++;
            StringBuilder sb = new StringBuilder(count + ". " + str);
            String numb = DATA.get(str).toString();
            if (numb.length() < 11) {
                String tmp = "";
                for (int i = 0; i < 11 - numb.length(); i++) {
                    tmp = "?" + tmp;
                }
                numb = tmp + numb;
            }
            sb.append(" - +").append(numb.substring(0, numb.length() - 10));
            sb.append(" (").append(numb.substring(numb.length() - 10, numb.length() - 7)).append(") ");
            sb.append(numb.substring(numb.length() - 7, numb.length() - 4)).append(" ");
            sb.append(numb.substring(numb.length() - 4, numb.length() - 2)).append(" ")
                    .append(numb.substring(numb.length() - 2));
            System.out.println(sb);
        }
    }
}
