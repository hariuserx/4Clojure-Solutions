#(cons % ((fn pascal [arg]
            (let [f (first arg)
                  l (last arg)]
              (loop [res [f] size (- (count arg) 1) i 0 arg arg]
                (if (= i size)
                  (let [res (conj res l)]
                    (lazy-seq (cons res (pascal res))))
                  (recur (conj res (+' (first arg) (second arg))) size (inc i) (drop 1 arg)))))) %))