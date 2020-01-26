package com.francochen.silentkiller.model;

public class Player {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Player) {
            return id.equals(((Player) object).getId());
        }

        return false;
    }
}
