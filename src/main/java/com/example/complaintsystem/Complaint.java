package com.example.complaintsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Pattern(
            regexp = "[A-Za-z ]+",
            message = "Username must contain only alphabets and spaces"
    )
    private String username;

    @NotBlank(message = "Department is required")
    @Pattern(
            regexp = "IT|HR|Finance|Admin",
            message = "department should be IT,HR,Finance,Admin"
    )
    private String department;

    @NotBlank(message = "Issue is required")
    @Size(min = 5,message = "Issue must contain atleast 5 characters")
    private String issue;

    private String status;

    @NotBlank(message = "Priority is required")
    @Pattern(
            regexp = "High|Medium|Low",
            message = "Priority should be High,Medium,Low"
    )
    private String priority;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email") //this validates email,used instead of pattern
    /*@Pattern(
            regexp = ".*@gmail\\.com$",
            message = "enter valid email"
    )*/
    private String email;

    private LocalDateTime createdat;

    private Complaint(){

    }

    //id - no need to write for setId(),because msql will automatically generate it,this prevents the user from sending the id
    public Long getId(){
        return id;
    }

    //username
    public void setUsername(String username){

        this.username = username;
    }
    public String getUsername(){

        return username;
    }

    //department
    public void setDepartment(String department){

        this.department = department;
    }
    public String getDepartment(){

        return department;
    }

    //issue
    public void setIssue(String issue){
        this.issue = issue;
    }
    public String getIssue(){

        return issue;
    }

    //status
    public void setStatus(String status){

        this.status = status;
    }
    public String getStatus(){

        return status;
    }

    //priority
    public void setPriority(String priority){

        this.priority = priority;
    }
    public String getPriority(){
        return priority;
    }

    //localdatetime
    public void setCreatedat(LocalDateTime createdat){

        this.createdat = createdat;
    }
    public LocalDateTime getCreatedat(){

        return createdat;
    }

    //email
    public void setEmail(String email){

        this.email = email;
    }
    public String getEmail(){

        return email;
    }
}
