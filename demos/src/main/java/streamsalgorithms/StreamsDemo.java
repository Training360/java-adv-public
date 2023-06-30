package streamsalgorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John Doe", 1970),
                new Employee("Jack Doe", 1990),
                new Employee("Jane Doe", 1980),
                new Employee("John Smith", 1960)
        );

        // Összegzés tétele: születési évük összege
        int sum = employees
                .stream()
                .mapToInt(Employee::getDateOfBirth)
                .sum();
        System.out.println(sum);

        // Életkoruk összege
        sum = employees
                .stream()
                .mapToInt(Employee::getDateOfBirth)
                .map(n -> 2022 - n)
                .sum();
        System.out.println(sum);

        // Számlálás tétele: hány alkalmazott van?
        long count = employees
                .stream()
                .count();
        System.out.println(count);

        // Hány alkalmazott született korábban, mint 1980
        count = employees
                .stream()
                .filter(e -> e.getDateOfBirth() < 1980)
                .count();
        System.out.println(count);

        // Szélsőérték keresés tétele: legkorábban született születési éve
        int min = employees
                .stream()
                .mapToInt(Employee::getDateOfBirth)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("Empty"));
        System.out.println(min);

        // Szélsőérték keresés tétele: legkorábban született neve
        String name = employees
                .stream()
                .sorted(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o1.getDateOfBirth() - o2.getDateOfBirth();
                    }
                })
                .map(e -> e.getName())
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Empty"));
        System.out.println(name);

        // Eldöntés tétele: Mindegyik 1980 előtt született?
        boolean before = employees.stream().allMatch(e -> e.getDateOfBirth() < 1980);
        System.out.println(before);
        before = employees.stream().allMatch(e -> e.getDateOfBirth() < 2000);
        System.out.println(before);

        // Szűrés: 1990-nél korábban született alkalmazott
        System.out.println(employees
                .stream()
                .filter(e -> e.getDateOfBirth() < 1990)
                .collect(Collectors.toList())
        );

        // Transzformáció: alkalmazottak nevei
        System.out.println(employees
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
        );

        // Kombinálva: 1990-nél korábban született alkalmazottak nevei
        System.out.println(employees
                .stream()
                .filter(e -> e.getDateOfBirth() < 1990)
                .map(Employee::getName)
                .collect(Collectors.toList())
        );
    }
}
