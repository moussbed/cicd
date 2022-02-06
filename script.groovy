class Person {
    String name
}

class Car {
    String name
}
def person = new Person(name:'Igor')
name="Bedril"

def cl = { name.toUpperCase() }
cl.delegate=person
cl.resolveStrategy=Closure.DELEGATE_FIRST
assert cl() == 'IGOR'


Car car = ["name":"Peugeot"]
cl.delegate=car
assert cl() == 'PEUGEOT'