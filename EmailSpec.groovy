class EmailSpec{

    void from (String from){
        println "From : $from"
    }
    void to (String... to){
         println "To : $to"
    }
    void subject (String subject){
        println "Subject : $subject"
    }

    void body(Closure cl){
        def bodySpec = new BodySpec()
        def code = cl.rehydrate(bodySpec,this,this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}