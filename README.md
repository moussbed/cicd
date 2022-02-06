## Continuous Integration and Continuous Deployment (CI/CD)

### Prerequisites 
Link : https://github.com/moussbed/cicd
- Groovy closure
- DSL (Domain Specific Language)

#### Groovy closure

- Definition :

A closure in Groovy is an open, anonymous, block of code that can take arguments, return a value and be assigned to a variable.
A closure may reference variables declared in its surrounding scope. In opposition to the formal definition of a closure, Closure in the Groovy language can also contain free variables which are defined outside of its surrounding scope.

- Syntax 

```groovy
   def myClosure= { item++ } 
   def item=10
   assert myClosure()==11
```

- Delegation strategy

  - Groovy closures vs lambda expressions
  
    Groovy defines closures as instances of the Closure class. It makes it very different from lambda expressions in Java 8. Delegation is a key concept in Groovy closures which has no equivalent in lambdas. The ability to change the delegate or change the delegation strategy of closures make it possible to design beautiful domain specific languages (DSLs) in Groovy.
  
  - Owner, delegate and this
  
    To understand the concept of delegate, we must first explain the meaning of this inside a closure. A closure actually defines 3 distinct things:
  
      - this:  corresponds to the enclosing class where the closure is defined
      - owner: corresponds to the enclosing object where the closure is defined, which may be either a class or a closure
      - delegate: corresponds to a third party object where methods calls or properties are resolved whenever the receiver of the message is not defined.
 
  - Delegate of a closure

    The delegate of a closure can be accessed by using the delegate property or calling the getDelegate method. It is a powerful concept for building domain specific languages in Groovy. While this and owner refer to the lexical scope of a closure, the delegate is a user defined object that a closure will use. By default, the delegate is set to owner.

    Whenever, in a closure, a property is accessed without explicitly setting a receiver object, then a delegation strategy is involved:
    
    ```groovy
      
      class Person {
         String name
      }
     def p = new Person(name:'Igor') 
     def cl = { name.toUpperCase() }   // 1           
     cl.delegate = p                  // 2              
     assert cl() == 'IGOR'           // 3
    ```
    
     1 ) name is not referencing a variable in the lexical scope of the closure
  
     2 ) we can change the delegate of the closure to be an instance of Person
    
     3 ) and the method call will succeed
     
    - Explanation
      The reason this code works is that the name property will be resolved transparently on the delegate object! This is a very powerful way to resolve properties or method calls inside closures. There’s no need to set an explicit delegate. receiver: the call will be made because the default delegation strategy of the closure makes it so. A closure actually defines multiple resolution strategies that you can choose:
        - Closure.OWNER_FIRST is the default strategy. If a property/method exists on the owner, then it will be called on the owner. If not, then the delegate is used.
        - Closure.DELEGATE_FIRST reverses the logic: the delegate is used first, then the owner
        - Closure.OWNER_ONLY will only resolve the property/method lookup on the owner: the delegate will be ignored.
        - Closure.DELEGATE_ONLY will only resolve the property/method lookup on the delegate: the owner will be ignored.
        - Closure.TO_SELF can be used by developers who need advanced meta-programming techniques and wish to implement a custom resolution strategy: the resolution will not be made on the owner or the delegate but only on the closure class itself. It makes only sense to use this if you implement your own subclass of Closure.

           