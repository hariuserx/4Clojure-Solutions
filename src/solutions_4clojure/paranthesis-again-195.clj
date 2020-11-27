(fn paranthesis-again [n]
  (set ((fn helper [result c l r value]
          (if (and (= 0 l) (= 0 r))
            (conj result value)
            (let [a (if (and (> c 0) (> r 0)) (helper result (dec c) l (dec r) (str value \))) [])
                  b (if (> l 0) (helper result (inc c) (dec l) r (str value \()))]
              (concat result a b))))
        []
        0
        n
        n
        "")))