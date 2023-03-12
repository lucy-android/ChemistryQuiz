package by.budanitskaya.l.chemistryquiz.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import by.budanitskaya.l.chemistryquiz.databinding.CustomDialogLayoutBinding
import by.budanitskaya.l.chemistryquiz.utils.view.ViewExtensions.click
import by.budanitskaya.l.chemistryquiz.utils.view.ViewExtensions.toVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CustomDialog(context: Context) {
    private val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)

    class Builder(private val root: ViewGroup) {
        private val customAlertDialog: CustomDialog = CustomDialog(root.context)

        private val dialogBinding =
            CustomDialogLayoutBinding.inflate(
                LayoutInflater.from(root.context), root, false
            )

        init {
            customAlertDialog.setView(dialogBinding.root as ViewGroup)
        }

        fun setCancelable(isCancelable: Boolean): Builder {
            customAlertDialog.setCancelable(isCancelable)
            return this
        }

        fun setMessageText(textRes: Int): Builder {
            dialogBinding.textViewMessage.toVisible()
            dialogBinding.textViewMessage.text = root.context.getString(textRes)
            return this
        }

        fun setDarkButtonText(okButtonTextRes: Int): Builder {
            dialogBinding.buttonDark.toVisible()
            dialogBinding.buttonDark.text = root.context.getString(okButtonTextRes)
            return this

        }

        fun setLightButtonText(cancelTextRes: Int): Builder {
            dialogBinding.buttonLight.toVisible()
            dialogBinding.buttonLight.text = root.context.getString(cancelTextRes)
            return this
        }

        fun setIcon(iconRes: Int): Builder {
            dialogBinding.imageViewIcon.toVisible()
            dialogBinding.imageViewIcon.setImageResource(iconRes)
            return this
        }

        fun setDarkButtonClickEvent(lambda: () -> Unit): Builder {
            dialogBinding.buttonDark.toVisible()
            dialogBinding.buttonDark click { lambda.invoke() }
            return this
        }

        fun setLightButtonClickEvent(lambda: () -> Unit): Builder {
            dialogBinding.buttonLight.toVisible()
            dialogBinding.buttonLight click { lambda.invoke() }
            return this
        }

        fun build(): AlertDialog {
            return customAlertDialog.build()
        }

        fun setTitle(titleResId: Int?): Builder {
            if (titleResId == null) return this
            dialogBinding.textViewTitle.toVisible()
            dialogBinding.textViewTitle.text = root.context.getString(titleResId)
            return this
        }

    }

    private fun build(): AlertDialog {
        return materialAlertDialogBuilder.create()
    }

    private fun setCancelable(cancelable: Boolean) {
        materialAlertDialogBuilder.setCancelable(cancelable)
    }

    private fun setView(root: ViewGroup) {
        materialAlertDialogBuilder.setView(root)
    }

}