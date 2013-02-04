(function ($) {
    T5.extendInitializers(function() {

        function init(specs) {
            $("#" + specs.id).blur(function () {
                    if (this.value) {
                        this.value = $.trim(this.value);
                    }
                }
            );
        }

        return {
            trimValue : init
        };
    });

})(jQuery);