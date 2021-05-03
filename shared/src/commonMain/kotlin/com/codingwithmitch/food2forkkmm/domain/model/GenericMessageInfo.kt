package com.codingwithmitch.food2forkkmm.domain.model

import com.codingwithmitch.shared.domain.util.MessageType
import com.codingwithmitch.shared.domain.util.UIComponentType

class GenericMessageInfo
private constructor(builder: GenericMessageInfo.Builder){

    // required
    val id: String
    val title: String
    val messageType: MessageType
    val uiComponentType: UIComponentType

    // optional
    val onDismiss: (() -> Unit)?
    val description: String?
    val positiveAction: PositiveAction?
    val negativeAction: NegativeAction?

    init {
        if(builder.id == null){
            throw Exception("GenericDialog id cannot be null.")
        }
        if(builder.title == null){
            throw Exception("GenericDialog title cannot be null.")
        }
        if(builder.messageType == null){
            throw Exception("GenericDialog messageType cannot be null.")
        }
        if(builder.uiComponentType == null){
            throw Exception("GenericDialog uiComponentType cannot be null.")
        }
        this.id = builder.id!!
        this.title = builder.title!!
        this.messageType = builder.messageType!!
        this.uiComponentType = builder.uiComponentType!!
        this.onDismiss = builder.onDismiss
        this.description = builder.description
        this.positiveAction = builder.positiveAction
        this.negativeAction = builder.negativeAction
    }

    class Builder {

        var id: String? = null
            private set

        var title: String? = null
            private set

        var onDismiss: (() -> Unit)? = null
            private set

        var messageType: MessageType? = null
            private set

        var uiComponentType: UIComponentType? = null
            private set

        var description: String? = null
            private set

        var positiveAction: PositiveAction? = null
            private set

        var negativeAction: NegativeAction? = null
            private set

        fun id(id: String): Builder{
            this.id = id
            return this
        }

        fun title(title: String): Builder{
            this.title = title
            return this
        }

        fun onDismiss(onDismiss: () -> Unit): Builder{
            this.onDismiss = onDismiss
            return this
        }

        fun messageType(
            messageType: MessageType
        ) : Builder {
            this.messageType = messageType
            return this
        }

        fun uiComponentType(
            uiComponentType: UIComponentType
        ) : Builder {
            this.uiComponentType = uiComponentType
            return this
        }

        fun description(
            description: String
        ): Builder{
            this.description = description
            return this
        }

        fun positive(
            positiveAction: PositiveAction?,
        ) : Builder {
            this.positiveAction = positiveAction
            return this
        }

        fun negative(
            negativeAction: NegativeAction
        ) : Builder {
            this.negativeAction = negativeAction
            return this
        }

        fun build() = GenericMessageInfo(this)
    }
}

data class PositiveAction(
    val positiveBtnTxt: String,
    val onPositiveAction: () -> Unit,
)

data class NegativeAction(
    val negativeBtnTxt: String,
    val onNegativeAction: () -> Unit,
)