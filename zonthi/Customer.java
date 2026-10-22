package zonthi;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private int customer_ID;
    private String customer_Name;   // Ho va ten day du
    private String customer_Gender; // "Nam" hoac "Nu"
    private int customer_Age;
    private String customer_Blood;  // Nhom mau: A, B, O, AB

    // Con trai / con phai trong BST
    public Customer left;
    public Customer right;

    // Constructor day du
    public Customer(int customer_ID, String customer_Name,
                    String customer_Gender, int customer_Age,
                    String customer_Blood) {
        this.customer_ID     = customer_ID;
        this.customer_Name   = customer_Name;
        this.customer_Gender = customer_Gender;
        this.customer_Age    = customer_Age;
        this.customer_Blood  = customer_Blood;
        this.left  = null;
        this.right = null;
    }

    // Constructor chi ID (dung khi tim kiem)
    public Customer(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    // ===================== Getters / Setters =====================
    public int getCustomer_ID()               { return customer_ID; }
    public void setCustomer_ID(int id)        { this.customer_ID = id; }

    public String getCustomer_Name()           { return customer_Name; }
    public void setCustomer_Name(String n)     { this.customer_Name = n; }

    public String getCustomer_Gender()         { return customer_Gender; }
    public void setCustomer_Gender(String g)   { this.customer_Gender = g; }

    public int getCustomer_Age()               { return customer_Age; }
    public void setCustomer_Age(int age)       { this.customer_Age = age; }

    public String getCustomer_Blood()          { return customer_Blood; }
    public void setCustomer_Blood(String b)    { this.customer_Blood = b; }
    public int getId() { return customer_ID; }
    public int getAge() { return customer_Age; }

    @Override
    public String toString() {
        return String.format("%-6d | %-25s | %-4s | %-3d | %s",
                customer_ID, customer_Name, customer_Gender,
                customer_Age, customer_Blood);
    }
}
