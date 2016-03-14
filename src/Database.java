import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Akrylic on 3/8/2016.
 */
public class Database {
    static Attribute classifier; //Either e/p in this case. This should be the same for the entire database
    ArrayList<Attribute> attributes = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();

    public Database(String file)
    {
    Reader n = new Reader(file);
    List<String> s = n.nextLine(); //this is literally just to get the size of the list
    for(int i = 0;i<s.size();i++) //Create a list of attributes by names according to the index of each Item
    {
        Reader newRead = new Reader(file);
        HashMap<String, String> tempMap = new HashMap<>(); //Just an easy thing we convert to a String list later
        while (!newRead.endOfFile)
        {
            List<String> tempList = newRead.nextLine();
            tempMap.put(tempList.get(i), tempList.get(i));
        }


        ArrayList<String> listToAddToAttribute = new ArrayList<>(); //Creating the list that gets passed into Attribute
        for (Map.Entry<String, String> me : tempMap.entrySet())
        {
            listToAddToAttribute.add(me.getValue());
        }
        //System.out.println("List added to attributes: "+listToAddToAttribute);
        attributes.add(new Attribute(Integer.toString(i), listToAddToAttribute)); //Done, a new attribute with a name and it's possible values is made
        //System.out.println("Column number "+i+" List added to attribute "+listToAddToAttribute);
        //for(Attribute a: attributes) {
        //    System.out.println(a.getPossibleValues());

        classifier = attributes.get(0); //Classifier chosen HERE
    }

    System.out.println("Loaded all Attributes by name (Index Number) and types");


    Reader r = new Reader(file);
    while(!r.endOfFile) //Now that
    // there are a bunch of attributes to classify by, each line Item needs to be processed
    {
        List<String> l = r.nextLine();
        Item item = new Item();
        for (int i = 0; i < l.size(); i++) {
            item.addType(attributes.get(i).name, l.get(i)); // Add to the attribute set
        }
        items.add(item);
    }
    }
    public ArrayList<Attribute> getAttributes()
    {
        for (Attribute a: attributes){
            System.out.println("Possible values of attributes "+a.getPossibleValues());
        }
        return attributes;
    }
    public ArrayList<Item> getItems()
    {
        return items;
    }
    public Attribute getClassifier()
    {
        System.out.println("Classifier (First column): "+classifier.getPossibleValues());
        return classifier;
    }
}
