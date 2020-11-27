(fn [val](clojure.string/join "," (filter #(let [s (Math/sqrt (Integer/parseInt %))]
                                             (>= (int s) s)) (clojure.string/split val #","))))