(fn [& args]
  (loop [res 0 infix (into [+] args)]
    (if (= 0 (count infix))
      res
      (let [ops (take 2 infix)
            operator (first ops)
            operand (second ops)
            infix (drop 2 infix)]
        (recur (operator res operand) infix)))))