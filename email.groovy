email {
    from 'not-reply@econbank.com'
    to 'dorgeles@yahoo.com'
    subject 'Your account is empty'
    body {
        p 'Your account is empty now. Please you can not purchase'
    }
}

def email (Closure cl){
   def emailSpec = new EmailSpec();
    def code = cl.rehydrate(emailSpec, null, null)
   code.resolveStrategy = Closure.DELEGATE_ONLY
    code()
}

