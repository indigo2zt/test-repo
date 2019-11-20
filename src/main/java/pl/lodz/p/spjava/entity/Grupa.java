package pl.lodz.p.spjava.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "grupa_konto")
@NamedQueries({
        @NamedQuery(name = "Grupa.findByLogin", query = "SELECT g FROM Grupa g where g.login = :login"),
        @NamedQuery(name = "Grupa.findAllPacjent", query = "SELECT g FROM Grupa g where g.nazwagrupy = 'Pacjent'"),
        @NamedQuery(name = "Grupa.usunByKonto", query = "DELETE FROM Grupa g where g.login = :konto")
})
public class Grupa implements Serializable {

    private static final long serialVersionUID = 2376578653453L;

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "grupaIdGen")
    private Long id;

    @JoinColumn(name = "login", referencedColumnName = "login", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Konto login;

    @Column(name = "nazwagrupy", nullable = false, length = 32)
    private String nazwagrupy;


    public Grupa() {

    }

    public Grupa(Konto login, String nazwagrupy) {
        this.login = login;
        this.nazwagrupy = nazwagrupy;
    }

    public Long getId() {
        return id;
    }


    public String getNazwagrupy() {
        return nazwagrupy;
    }

    public void setNazwagrupy(String nazwagrupy) {
        this.nazwagrupy = nazwagrupy;
    }

    public Konto getLogin() {
        return login;
    }

    public void setLogin(Konto login) {
        this.login = login;
    }
}
