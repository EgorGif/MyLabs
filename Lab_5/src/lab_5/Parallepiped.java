package lab_5;

import java.util.Iterator;
import java.lang.Object;
import java.util.Comparator;

interface Body {
    int area ();
    int volume ();
}

class Parallepiped implements Comparable <Parallepiped>,Iterable<Object>, Iterator<Object>, Body{
    private int length;
    private int width;
    private int height;
    private int iteratorIndex = 0;

    public Parallepiped () {
        length = 0;
        width = 0;
        height = 0;
    }

    public Parallepiped (int l, int w, int h){
        assert(l>0);
        length = l;
        assert (w>0);
        width = w;
        assert (h>0);
        height = h;
    }

    public Parallepiped (String str) {
        String[] string = str.split("\\s");
        assert (Integer.parseInt(string[0])>0);
        length = Integer.parseInt(string[0]);
        assert (Integer.parseInt(string[1])>0);
        width = Integer.parseInt(string[1]);
        assert (Integer.parseInt(string[2])>0);
        height = Integer.parseInt(string[2]);
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int area() {
        return (2*(length*width+length*height+width*height));
    }

    public int volume() {
        return (length*width*height);
    }

    public String toString() {
        return (getLength()+" "+getWidth()+" "+getHeight());
    }

    public int compareTo(Parallepiped entry) {
        if ( (this.volume() - entry.volume() > 0) ) {
            return 1;
        }
        if ( (this.volume() - entry.volume() < 0) ) {
            return -1;
        }
        return 0;
    }

    static class CompareToLength implements Comparator <Parallepiped> {
        public int compare(Parallepiped o1, Parallepiped o2) {
            if((o1.getLength() - o2.getLength()) > 0) {
                return 1;
            }
            else if((o1.getLength() - o2.getLength()) < 0) {
                return -1;
            }
            return 0;
        }
    }

    static class CompareToWidth implements Comparator <Parallepiped> {
        public int compare(Parallepiped o1, Parallepiped o2) {
            if((o1.getWidth() - o2.getWidth()) > 0) {
                return 1;
            }
            else if((o1.getWidth() - o2.getWidth()) < 0) {
                return -1;
            }
            return 0;
        }
    }

    static class CompareToHeight implements Comparator <Parallepiped> {
        public int compare(Parallepiped o1, Parallepiped o2) {
            if((o1.getHeight() - o2.getHeight()) > 0) {
                return 1;
            }
            else if((o1.getHeight() - o2.getHeight()) < 0) {
                return -1;
            }
            return 0;
        }
    }

    private void reset() {
        iteratorIndex = 0;
    }

    public boolean hasNext() {
        return iteratorIndex < 3;
    }

    public Object next() {
        switch(iteratorIndex){
            case 0:
                iteratorIndex++;
                return getLength();
            case 1:
                iteratorIndex++;
                return getWidth();
            case 2:
                iteratorIndex++;
                return getHeight();
            default:
                reset();
                return null;
        }
    }

    public void remove() {

    }

    public Iterator<Object> iterator() {
        return this;
    }

}