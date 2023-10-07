package tv.codelong.thenewboston.model

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_user_id_seq")
        @SequenceGenerator(name = "api_user_id_seq", allocationSize = 1)
        @Column(name = "id")
        var id: Long = 0,

        @Column(name = "name")
        var name: String = "",

        @Column(name = "user_name")
        var userName: String = "",

        @Column(name = "user_surname")
        var userSurname: String = "",

        @Column(name = "user_mail")
        var userMail: String = "",

        @Column(name = "user_phone")
        var userPhone: String = "",

        @Column(name = "password")
        var password: String = "",
)
