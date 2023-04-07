package org.example.conditions;

/**
 * Represents an entity that can be deleted but just logically.
 */
public interface HasDeleted {

    boolean isDeleted();

    void setDeleted(boolean deleted);
}
