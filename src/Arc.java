
public class Arc extends Node
{
    Node from;
    Node to;
    int str;

    public Arc(String n, Node f, Node t)
    {
        name = n;
        from = f;
        to = t;
        str =1;

    }

    public void setName(String n)
    {
        name = n;
    }

    public void setFrom(Node f)
    {
        from = f;
    }

    public void setTo(Node t)
    {
        to = t;
    }
}
