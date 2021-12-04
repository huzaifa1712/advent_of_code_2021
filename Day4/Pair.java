class Pair<F,S>  { 
    F first;
    S second;

    Pair(F first, S second) { 
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() { 
        return String.format("(%s, %s)", first.toString(), second.toString());
    }
}
