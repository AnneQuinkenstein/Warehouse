package admin;

import administration.Customer;

public class CustomerImpl implements Customer {
    private String name;

    protected CustomerImpl(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerImpl that = (CustomerImpl) o;
        return name.equals(that.name);
    }
    @Override
    public int hashCode(){
        return name.hashCode();
    }

}
