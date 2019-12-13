package ash.pickshunter.model

class ResponseBase {
    var error: Throwable? = null

    constructor(error: Throwable) {
        this.error = error
    }
}