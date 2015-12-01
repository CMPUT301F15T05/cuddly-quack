/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.ualberta.cs.swapmyride.Misc;

import java.util.UUID;

/**
 * UniqueID will allow us to add and create unique identifiers to classes such as trade or pictures
 * enabling an easy way to compare if the objects are related.
 */
public class UniqueID {
    private String id;

    public UniqueID() {
        // http://www.javacirecep.com/utility/java-ways-to-generate-unique-ids-in-java/
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Private constructor used for copying the unique id over in duplicateID
     */
    private UniqueID(String id) {
        this.id = id;
    }

    /**
     * Returns a new UniqueID object and duplicates the id field
     */
    public UniqueID duplicateID() {
        return new UniqueID(this.getID());
    }

    /**
     * Returns the string id
     */
    public String getID() {
        return id;
    }

    /**
     * Returns if the the two objects has the same id
     *
     * @param other is the second UniqueID object to compare to
     */
    public Boolean isEqualID(UniqueID other) {
        return (this.getID().equals(other.getID()));
    }

    public void setId(String myid) {
        this.id = myid;
    }
}
