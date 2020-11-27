(fn compute [num base]
  ((fn helper [num base result]
     (if (= 0 num)
       (if (empty? result)
         '(0)
         result)
       (helper (quot num base)
               base
               (conj result (rem num base)))))
   num base '()))