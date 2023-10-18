import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

enum EmployeeType {
    Officer, Staff
}

class Employee {
    private final int id;
    private final String name;
    private final LocalDate dateOfBirth;
    private final String email;
    private final LocalDate joiningDate;
    private final EmployeeType employeeType;

    public Employee(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate, EmployeeType employeeType) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.joiningDate = joiningDate;
        this.employeeType = employeeType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }
}

class LeaveCalculator {
    public static int calculateVacationLeave(Employee employee) {
        LocalDate yearEnd = LocalDate.of(employee.getJoiningDate().getYear(), 12, 31);
        long daysWorked = ChronoUnit.DAYS.between(employee.getJoiningDate(), yearEnd) + 1;
        int totalLeaveDays = (employee.getEmployeeType() == EmployeeType.Officer) ? 15 : 10;

        if (yearEnd.isLeapYear()) {
            return (int) Math.ceil((daysWorked * totalLeaveDays) / 366.0);
        } else {
            return (int) Math.ceil((daysWorked * totalLeaveDays) / 365.0);
        }
    }

    public static int calculateSickLeave(Employee employee) {
        LocalDate yearEnd = LocalDate.of(employee.getJoiningDate().getYear(), 12, 31);
        long daysWorked = ChronoUnit.DAYS.between(employee.getJoiningDate(), yearEnd) + 1;
        int totalLeaveDays = (employee.getEmployeeType() == EmployeeType.Officer) ? 10 : 7;

        if (yearEnd.isLeapYear()) {
            return (int) Math.ceil((daysWorked * totalLeaveDays) / 366.0);
        } else {
            return (int) Math.ceil((daysWorked * totalLeaveDays) / 365.0);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter employee information for employee " + (i + 1));
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Date of Birth (yyyy-MM-dd): ");
            LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Joining Date (yyyy-MM-dd): ");
            LocalDate joiningDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Employee Type (Officer/Staff): ");
            EmployeeType employeeType = EmployeeType.valueOf(scanner.nextLine());

            Employee employee = new Employee(id, name, dateOfBirth, email, joiningDate, employeeType);
            employees.add(employee);
        }

        System.out.println("\nEmployee Records");
        for (Employee employee : employees) {
            int vacationLeave = LeaveCalculator.calculateVacationLeave(employee);
            int sickLeave = LeaveCalculator.calculateSickLeave(employee);

            System.out.println("ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Date of Birth: " + employee.getDateOfBirth());
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Joining Date: " + employee.getJoiningDate());
            System.out.println("Employee Type: " + employee.getEmployeeType());
            System.out.println("Vacation Leave: " + vacationLeave);
            System.out.println("Sick Leave: " + sickLeave);
            System.out.println();
        }
    }
}
