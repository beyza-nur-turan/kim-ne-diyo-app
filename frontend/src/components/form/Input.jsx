import {  useField,  } from "formik";

import PropTypes from "prop-types";

function Input({ label,type, defaultValue,...props }) {
  const [field, meta, helpers] = useField(props);
  return (
    <label>
      <div>{label}</div>
      <input type={type} {...field} {...props} defaultValue={defaultValue}/>
    </label>
  );
}

Input.propTypes = {
  label: PropTypes.string.isRequired,
};

export default Input;
