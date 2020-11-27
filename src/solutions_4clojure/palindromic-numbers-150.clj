(let [next-palindrome (fn [n]
                        (let [n        (str n)
                              c        (count n)
                              left     (Long. (subs n 0 (Math/ceil (/ c 2))))
                              resulter (fn [left] (Long. (str left (subs (clojure.string/reverse (str left)) (if (even? c) 0 1)))))
                              r1       (resulter left)
                              r2       (resulter (str (inc left)))]
                          (if (>= r1 (Long. n)) r1 r2)))]
  #(iterate (comp next-palindrome inc) (next-palindrome %)))