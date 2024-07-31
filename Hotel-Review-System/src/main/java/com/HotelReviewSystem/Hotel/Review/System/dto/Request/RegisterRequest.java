package com.HotelReviewSystem.Hotel.Review.System.dto.Request;
import com.HotelReviewSystem.Hotel.Review.System.Model.Customer;
import com.HotelReviewSystem.Hotel.Review.System.Model.User;
import jakarta.validation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Email(message = "INVALID EMAIL ADDRESS")
    @NotBlank(message = "EMAIL CANNOT BE EMPTY")

    private String email;

    @NotBlank(message = "Password CANNOT BE EMPTY")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,20}$"
            ,message = "Password must be at least 8 characters long , at most 20 characters,contain at least one digit, one lowercase letter, one uppercase letter, and one letter.")
    private String password;


    @Size(min = 3,max = 20,message = "First name must be at least 8 characters long, at most 20 characters")
    private String firstname;


    @Size(min = 3,max = 20,message = "Last name must be at least 8 characters long, at most 20 characters")
    private String lastname;

    @Size(min = 0,max = 120,message = "Last name must be at least 20 characters long, at most 120 characters")
    private String address;

    @NotBlank(message="PHONE NUMBER CANNOT BE EMPTY")
    private String phonenumber;


   // @Pattern(regexp =   "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)" ,message = "Format is dd/mm/yyyy ")
    private String birthday;

    @NotBlank(message="Nationality CANNOT BE EMPTY")
    private String nationality;

    public User GetUser(){
        return User.builder().firstname(this.firstname).lastname(this.lastname).email(this.email).password(this.password).role("Customer").build();
    }
    public Customer GetCustomer(){
        return Customer.builder().address(this.address).birthday(this.birthday).phoneNumber(this.phonenumber).build();
    }
}
