package e_food_additive.zenolbs.com.efoodadditive.view;

/**
 * Created by grd on 2/8/17.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.util.Log;



public  class InfoDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        final SpannableString link = new SpannableString("https://www.bank.gov.ua");
        Linkify.addLinks(link, Linkify.ALL);

        builder.setTitle("О пищевых добавках")
                .setMessage("Пищевые добавки разделены на группы в зависимости от цели применения.\n" +
                        "\n" +
                        "Е100—Е199 — красители\n" +
                        "\n" +
                        "Придают \"аппетитный\" цвет. Содержатся в безалкогольных напитках, мороженом, кондитерских изделиях.\n" +
                        "\n" +
                        "Е200—Е299 — консерванты\n" +
                        "\n" +
                        "Предотвращают порчу продуктов. Находятся в большинстве консервов, шоколаде, чипсах, сухих супах, винах.\n" +
                        "\n" +
                        "Е300—Е399 — антиоксиданты\n" +
                        "\n" +
                        "Сохраняют вкус продукта. Находятся в жирных продуктах, вине, пиве, лимонадах, \"кисломолочке\", колбасах, масле, шоколаде.\n" +
                        "\n" +
                        "Е400—Е499 — стабилизаторы и загустители\n" +
                        "\n" +
                        "Поддерживают нужную консистенцию продукта. Часто находятся в вареньях, джемах, сгущенке.\n" +
                        "\n" +
                        "Е500—Е599 — эмульгаторы\n" +
                        "\n" +
                        "Обеспечивают однородность продукта. Могут быть в хлебе, вареньях, джемах, сгущенке.\n" +
                        "\n" +
                        "Е600—Е699 — усилители вкуса\n" +
                        "\n" +
                        "Усиливают естественные вкус и запах продукта. Применяются в блюдах фастфудов и восточной кухни.\n" +
                        "\n" +
                        "Е900 и далее — распушители, сахарозаменители ")


                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

    }
}
