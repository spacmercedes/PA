package com.example;

import java.util.*;

public class RelationsRepository {
    private final List<Relations> relations = new ArrayList<>();

    public RelationsRepository(){ }

    public void createRelationship(Relations relationship) {
        relations.add(relationship);
    }

    public boolean isPresent(Relations relationship) {
        for(Relations relationshipC: relations) {
            if(relationshipC.getId() == relationship.getId())
                return true;
        }
        return false;
    }

    public List<Relations> getAllRelations() {
        return relations;
    }
    public Relations getRelationship(String person1, String person2) {
        for(Relations relationshipC: relations) {
            if(relationshipC.getPerson1().equals(person1) && relationshipC.getPerson2().equals(person2))
                return relationshipC;
        }
        return null;
    }
    public  List<String> getTheMostPopularUsers(int nr){
        PersonController allPersonController = new PersonController();
        List<String> popularPerson = new ArrayList<>();
        HashMap<String, Integer> people = new HashMap<String, Integer>();
        for(Person person:allPersonController.persons)
        {
            people.put(person.getName(), 0);
        }
        for(Relations relationship:relations)
        {
            people.put(relationship.getPerson1(), people.get(relationship.getPerson1()) + 1);
            people.put(relationship.getPerson2(), people.get(relationship.getPerson2()) + 1);
        }
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(people.entrySet());
        // Sort
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Collections.reverse(list);
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        int k=0;
        for (String i : temp.keySet()) {
            popularPerson.add(i);
            ++k;
            if(k==nr)
                break;
        }
        return popularPerson;
    }

    public void deleteRelationship(String person1, String person2)
    {
        for(Relations relationshipC: relations) {
            if(relationshipC.getPerson1().equals(person1) && relationshipC.getPerson2().equals(person2))
                relations.remove(relationshipC);
        }
    }


}
