(fn [word] (clojure.string/replace word
                                   #"-([a-z])"
                                   #(.toUpperCase (%1 1))))