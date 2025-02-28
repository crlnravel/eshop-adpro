package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class Item {
    private String id;

    Item() {
        this.id = UUID.randomUUID().toString();
    }
}
