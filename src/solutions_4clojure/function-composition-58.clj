(fn  [& args]
  (let [size (count args)
        build (fn builder [i x y]
                (if (= i (- size 1))
                  (if (nil? y)
                    ((nth args i) x)
                    (apply (partial (nth args i) x) y))
                  ((nth args i) (builder (inc i) x y))))]
    (fn [x & y] (build 0 x y))))