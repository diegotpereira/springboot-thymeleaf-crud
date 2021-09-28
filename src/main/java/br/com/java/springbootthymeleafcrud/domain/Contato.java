package br.com.java.springbootthymeleafcrud.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.validation.annotation.Validated;

// import lombok.Getter;
// import lombok.Setter;

import javax.validation.constraints.Email;
// import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
@Entity
@Table(name = "tb_contato")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
// @Getter
// @Setter
public class Contato implements Serializable {
    
    private static final long serialVersionUID = 4048798961366546485L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "Telefone número")
    @Size(max = 25)
    private String telefone;

    @Email(message = "Endereço Email")
    @Size(max = 100)
    private String email;

    @Size(max = 50)
    private String endereco1;

    @Size(max = 50)
    private String endereco2;

    @Size(max = 50)
    private String endereco3;

    @Size(max = 20)
    private String cep;

    @Column(length = 4000)
    private String nota;

    public Contato() {
    }

    

    public Contato(Long id, @NotBlank @Size(max = 100) String nome,
            @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Telefone número") @Size(max = 25) String telefone,
            @Email(message = "Endereço Email") @Size(max = 100) String email, @Size(max = 50) String endereco1,
            @Size(max = 50) String endereco2, @Size(max = 50) String endereco3, @Size(max = 20) String cep,
            String nota) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco1 = endereco1;
        this.endereco2 = endereco2;
        this.endereco3 = endereco3;
        this.cep = cep;
        this.nota = nota;
    }



    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco1() {
        return endereco1;
    }

    public void setEndereco1(String endereco1) {
        this.endereco1 = endereco1;
    }

    public String getEndereco2() {
        return endereco2;
    }

    public void setEndereco2(String endereco2) {
        this.endereco2 = endereco2;
    }

    public String getEndereco3() {
        return endereco3;
    }

    public void setEndereco3(String endereco3) {
        this.endereco3 = endereco3;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Contato [cep=" + cep + ", email=" + email + ", endereco1=" + endereco1 + ", endereco2=" + endereco2
                + ", endereco3=" + endereco3 + ", id=" + id + ", nome=" + nome + ", nota=" + nota + ", telefone="
                + telefone + "]";
    }
}
