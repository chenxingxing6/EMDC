BEGIN
   FOR a in 1 .. 31 LOOP
   execute IMMEDIATE
				'delete from e_detail_' ||a;
  END LOOP;
END;
/